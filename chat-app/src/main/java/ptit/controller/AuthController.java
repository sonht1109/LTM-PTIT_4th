package ptit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptit.base.BaseResponse;
import ptit.base.JwtUtils;
import ptit.base.MyUserDetails;
import ptit.dto.AuthLoginDto;
import ptit.dto.AuthSignupDto;
import ptit.entity.UserEntity;
import ptit.service.UserService;

@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse<Object> login(@RequestBody AuthLoginDto login) {
        UserEntity user = userService.findByEmail(login.getEmail());

        if(user != null){
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            }
            catch (BadCredentialsException e) {
                throw new BadCredentialsException("Invalid username " + login.getEmail());
            }
            MyUserDetails myUserDetails = (MyUserDetails) userService.loadUserByUsername(login.getEmail());
            String jwt = JwtUtils.generateToken(myUserDetails);
            return BaseResponse.builder().message("Đăng nhập thành công.").code("200").body(jwt).build();
        }

        return BaseResponse.builder().message("Đăng nhập thất bại.").code("400").body(null).build();
    }

    @PostMapping("/signup")
    public BaseResponse<Object> signup(@RequestBody AuthSignupDto signup) {
        // check mapping pass
        if(!signup.getPassword1().equals(signup.getPassword2())){
            return BaseResponse.builder().message("Mật khẩu không khớp.").code("400").body(null).build();
        }

        // check email exist
        if(userService.findByEmail(signup.getEmail()) != null){
            return BaseResponse.builder().message("Email đã tồn tại.").code("400").body(null).build();
        }

        UserEntity newUser = new UserEntity(signup.getEmail(), signup.getPassword1());
        newUser.setUsername(signup.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(signup.getPassword1()));
        UserEntity res = userService.addUser(newUser);

        if(res.getId() != null){
            return BaseResponse.builder().message("Đăng ký tài khoản thành công.").code("200").body(res).build();
        }

        return null;
    }
}
