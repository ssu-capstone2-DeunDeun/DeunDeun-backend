package kr.co.deundeun.groopy.service;

import javax.transaction.Transactional;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.dto.alarm.AlarmResponseDto;
import kr.co.deundeun.groopy.dao.AlarmRepository;
import kr.co.deundeun.groopy.domain.alarm.Alarm;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.dto.message.MessageRequest;
import kr.co.deundeun.groopy.helper.ClubHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AlarmService {

  private final AlarmRepository alarmRepository;

  private final ClubRepository clubRepository;

  private final MailService mailService;

  private final MessageService messageService;

  public List<AlarmResponseDto> getAlarm(User user) {
    List<Alarm> alarms = alarmRepository.findAllByUserId(user.getId());
    return alarms.stream()
                 .map(AlarmResponseDto::new)
                 .collect(Collectors.toList());
  }

  public void notify(MessageRequest messageRequest) {
    Club club = ClubHelper.findClubById(clubRepository, messageRequest.getClubId());
    mailService.sendMail(messageRequest, club);
    messageService.sendMessage(messageRequest, club);
  }

}
