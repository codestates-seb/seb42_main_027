package ynzmz.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class SingleResponseDto<T> {
    private T data;

}
