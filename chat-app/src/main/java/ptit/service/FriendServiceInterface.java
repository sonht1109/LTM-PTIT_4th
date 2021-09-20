package ptit.service;

import org.springframework.data.jpa.repository.Query;
import ptit.entity.FriendEntity;
import ptit.entity.UserEntity;

import java.util.List;

public interface FriendServiceInterface {
    FriendEntity addFriend(UserEntity user, UserEntity friend);
    FriendEntity confirmFriend(Long friend_id, Boolean status);
    List<FriendEntity> getListFriend(String username);

}
