package kr.co.kshproject.webDemo.Domain.Notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeListDTO {
    private HashMap<String,Object> noticeListDTO;
}
