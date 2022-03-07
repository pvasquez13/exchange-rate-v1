package com.bcp.services.cross.exchangerate.utils;

import com.bcp.services.cross.exchangerate.utils.constant.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class Util {

  @Value("${application.session-time}")
  private Long sessionTime;

  public String generateRandomAlphanumericString() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    return random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
  }

  public String currentDateTime(LocalDateTime today) {
    return today.format(DateTimeFormatter.ofPattern(Constants.PATTERN_DATE));
  }

  public String getDueDateTime(LocalDateTime today) {
    LocalDateTime dueDate = today.plusMinutes(sessionTime);
    return dueDate.format(DateTimeFormatter.ofPattern(Constants.PATTERN_DATE));
  }

}
