package ptit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptit.base.BaseResponse;
import ptit.base.MyUserDetails;
import ptit.dto.FriendConfirmDto;
import ptit.dto.UserAddFriendDto;
import ptit.entity.FriendEntity;
import ptit.entity.UserEntity;
import ptit.repository.FriendRepository;
import ptit.service.FriendService;
import ptit.service.UserService;

@RestController
@RequestMapping("/api")
public class FriendController {
    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @PostMapping("/add-friend")
    public BaseResponse<Object> addFriend(@RequestBody UserAddFriendDto userAddFriendDto){
        MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity friendUser = userService.findOne(userAddFriendDto.getFriend_id());

        if(friendUser == null && friendUser.getId() == currentUser.getUserEntity().getId()){
            return BaseResponse.builder().code("400").message("Friend Id không hợp lệ.").build();
        }

        // add friend
        FriendEntity friendEntity = friendService.addFriend(currentUser.getUserEntity(), friendUser);
        if(friendEntity != null){
            return BaseResponse.builder().code("200").message("Gưi lời mời kết bạn thành công.").body(friendEntity).build();
        }
        return BaseResponse.builder().code("400").message("Friend Id không hợp lệ.").build();
    }

    @PostMapping("/confirm-friend")
    public BaseResponse<Object> confirmFriend(@RequestBody FriendConfirmDto friendConfirmDto){
        FriendEntity friendEntity = friendService.confirmFriend(friendConfirmDto.getFriend_id());
        // System.out.println(friendEntity.toString());
        if(friendEntity != null){
            return BaseResponse.builder().code("200").message("Kết bạn thành công.").body(friendEntity).build();
        }
        return BaseResponse.builder().code("400").message("Không thành công.").body(null).build();
    }
}
