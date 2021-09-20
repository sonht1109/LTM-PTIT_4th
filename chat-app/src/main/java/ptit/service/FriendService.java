package ptit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.entity.FriendEntity;
import ptit.entity.UserEntity;
import ptit.repository.FriendRepository;
import ptit.repository.UserRepository;

import java.util.List;

@Service
public class FriendService implements FriendServiceInterface{
    @Autowired
    FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public FriendEntity addFriend(UserEntity user, UserEntity friend) {
        FriendEntity checkDuplicate = friendRepository.findFirstByUser_id_1AndUser_id_2(user.getId(), friend.getId());
        if(checkDuplicate == null){
            FriendEntity friendEntity = new FriendEntity();
            friendEntity.setUser_id_1(user);
            friendEntity.setUser_id_2(friend);
            friendEntity.setConfirmed(false);
            return friendRepository.save(friendEntity);
        }
       return null;
    }

    @Override
    public FriendEntity confirmFriend(Long friend_id) {
        FriendEntity friendEntity = friendRepository.findById(friend_id).orElse(null);
        // System.out.println(friendEntity.toString()+"  <<<<<<<<<");
        if(friendEntity != null){
            friendEntity.setConfirmed(true);
            return friendRepository.save(friendEntity);
        }

        return null;
    }

    @Override
    public List<FriendEntity> getListFriend(String username) {
        return null;
    }
}
