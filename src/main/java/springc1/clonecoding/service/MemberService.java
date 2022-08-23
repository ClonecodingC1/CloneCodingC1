package springc1.clonecoding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springc1.clonecoding.controller.request.*;
import springc1.clonecoding.controller.response.ResponseDto;
import springc1.clonecoding.domain.Member;
import springc1.clonecoding.jwt.TokenProvider;
import springc1.clonecoding.repository.MemberRepository;
import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public ResponseDto<?> createMember(SignupRequestDto requestDto) {

        isPresentUserName(requestDto.getUsername());
        isPresentNickName(requestDto.getUsername());

        String password = passwordEncoder.encode(requestDto.getPassword());

        Member member = new Member(requestDto, password);

        memberRepository.save(member);
        return ResponseDto.success("success");

    }


    @Transactional
    public ResponseDto<?> userCheck(UserCheckRequestDto requestDto) {
        isPresentUserName(requestDto.getUsername());
        return ResponseDto.success("success");
    }


    @Transactional
    public ResponseDto<?> nickCheck(NickCheckRequestDto requestDto) {
        isPresentNickName(requestDto.getNickname());
        return ResponseDto.success("success");
    }

    @Transactional
    public ResponseDto<?> login(LoginRequestDto requestDto, HttpServletResponse response) {

        Member member = memberRepository.findByUsername(requestDto.getUsername()).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        passwordCheck(member.getPassword(), requestDto.getPassword());

        TokenDto tokenDto = tokenProvider.generateTokenDto(member);

        tokenToHeaders(tokenDto, response);

        return ResponseDto.success("success");

    }
    private void passwordCheck(String password, String comfirmPassword) {
        if (passwordEncoder.matches(password, comfirmPassword)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }


    public void tokenToHeaders(TokenDto tokenDto, HttpServletResponse response) {
        response.addHeader("Access_Token", "Bearer " + tokenDto.getAccessToken());
    }

    @Transactional(readOnly = true)
    public void isPresentUserName(String username) {
        if(memberRepository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException("중복된 아이디가 존재합니다");
        }
    }
    @Transactional(readOnly = true)
    public void isPresentNickName(String nickname) {
        if(memberRepository.findByNickname(nickname).isPresent()){
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다");
        }
    }

}
