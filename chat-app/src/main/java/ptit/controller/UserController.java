package ptit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ptit.base.BaseResponse;
import ptit.base.MyUserDetails;
import ptit.dto.UserAddFriendDto;
import ptit.entity.UserEntity;
import ptit.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Object> getCurrentUser() {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user.getUserEntity());
    }

    @GetMapping("/list-user")
    public BaseResponse<Object> getListUser(@RequestParam(required = false, defaultValue = "") String username){
        List<UserEntity> userEntityList = userService.findAllByUsername(username);
        return BaseResponse.builder().code("200").message("Thành công.").body(userEntityList).build();
    }

    @PostMapping("/add-friend")
    public BaseResponse<Object> addFriend(@RequestBody UserAddFriendDto userAddFriendDto){
        MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity friendUser = userService.findOne(userAddFriendDto.getFriend_id());

        if(friendUser == null && friendUser.getId() == currentUser.getUserEntity().getId()){
            return BaseResponse.builder().code("400").message("Friend Id không hợp lệ.").build();
        }

        return BaseResponse.builder().code("200").message("Gưi lời mời kết bạn thành công.").build();
    }
}
