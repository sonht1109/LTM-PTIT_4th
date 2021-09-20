package ptit.service;

import ptit.entity.FriendEntity;
import ptit.entity.UserEntity;

import java.util.List;

public interface FriendServiceInterface {
    FriendEntity addFriend(UserEntity user, UserEntity friend);
    FriendEntity confirmFriend(Long friend_id);
    List<FriendEntity> getListFriend(String username);
}
