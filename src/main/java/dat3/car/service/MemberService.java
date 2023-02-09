package dat3.car.service;

import dat3.car.dto.MemberRequestDto;
import dat3.car.dto.MemberResponseDto;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponseDto> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponse = members.stream().map(m -> new MemberResponseDto(m, includeAll)).toList();
        return memberResponse;
    }

    public MemberResponseDto getMember(String username, boolean includeAll) {
        Optional<Member> member = memberRepository.findByUsername(username);
        List<MemberResponseDto> memberResponse = member.stream().map(m -> new MemberResponseDto(m, includeAll)).toList();
        return memberResponse.get(0);
    }

    public MemberResponseDto addMember(MemberRequestDto memberRequest) {
        if(memberRepository.existsByUsername(memberRequest.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this Username already exist");
        }
        if(memberRepository.existsByEmail(memberRequest.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this Email already exist");
        }
        Member newMember = MemberRequestDto.getMemberEntity(memberRequest);
        newMember = memberRepository.save(newMember);
        return new MemberResponseDto(newMember, false);
    }

    public void setMemberRanking(String username, int value) {
        Optional<Member> member = memberRepository.findByUsername(username);
        if (member.isPresent()) {
            Member newMember = member.get();
            newMember.setRanking(value);
            memberRepository.save(newMember);
        }
    }

    public void deleteMember(String username) {
        memberRepository.deleteByUsername(username);
    }

    public ResponseEntity<Boolean> updateMember(MemberRequestDto body, String username) {
        Member newMember = MemberRequestDto.getMemberEntity(body);
        return memberRepository.update(username, newMember);
    }
}
