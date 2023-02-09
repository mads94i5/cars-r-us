package dat3.car.api;

import dat3.car.dto.MemberRequestDto;
import dat3.car.dto.MemberResponseDto;
import dat3.car.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {
    final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //Security Admin
    @GetMapping
    List<MemberResponseDto> getMembers(){
        return memberService.getMembers(false);
    }

    //Security Admin
    @GetMapping(path = "/{username}")
    MemberResponseDto getMemberById(@PathVariable String username) throws Exception {
        return memberService.getMember(username, false);
    }

    //Security Unauthorized
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MemberResponseDto addMember(@RequestBody MemberRequestDto body) {
        return memberService.addMember(body);
    }

    //Security Member
    @PutMapping("/{username}")
    ResponseEntity<Boolean> editMember(@RequestBody MemberRequestDto body, @PathVariable String username) {
        return memberService.updateMember(body, username);
    }

    //Security Admin
    @PatchMapping("/ranking/{username}/{value}")
    void setRankingForUser(@PathVariable String username, @PathVariable int value) {
        memberService.setMemberRanking(username, value);
    }

    // Security Admin
    @DeleteMapping("/{username}")
    void deleteMemberByUsername(@PathVariable String username) {
        memberService.deleteMember(username);
    }



}
