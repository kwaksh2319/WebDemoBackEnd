package kr.co.kshproject.webDemo.Applicaiton;

import kr.co.kshproject.webDemo.Domain.Notice;
import kr.co.kshproject.webDemo.Domain.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NoticeService implements NoticeServiceInterface{
    @Autowired
    private NoticeDao noticeDao;

    public void save(Notice notice, HttpSession session) {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(today);
        notice.setCreatedDate(dateString);

        Object attribute = session.getAttribute("user");
        if (attribute instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) attribute;
            notice.setUsername(user.getUsername());
        }

        String tmpContent=notice.getContents();
        String tmpEmail=notice.getEmail();
        String tmpTitle=notice.getTitle();

        if(tmpContent.length()>100) {
            tmpContent=tmpContent.substring(0, 100);
        }

        if(tmpEmail.length()>100){
            tmpEmail=tmpEmail.substring(0, 100);
        }

        if(tmpTitle.length()>100){
            tmpTitle=tmpTitle.substring(0, 100);
        }
        notice.setContents(ConverterStringToHtml(tmpContent));
        notice.setEmail(ConverterStringToHtml(tmpEmail));
        notice.setTitle(ConverterStringToHtml(tmpTitle));
        noticeDao.save(notice);
    }
    public List<Notice> findAll(int page, int size) {

        return noticeDao.findAll(page,size);
    }
    public void deleteNoticeDetail(Long id){
        noticeDao.delete(id);
    }

    public Notice findDetail(Long id) {
        Notice notice = null;
        notice=noticeDao.findDetail(id);
        notice.setContents(ConverterHtmlToStr(notice.getContents()));
        return notice;
    }
    public void update(Long id, Notice newNotice) {
        String tmpContent=newNotice.getContents();
        String tmpEmail=newNotice.getEmail();
        String tmpTitle=newNotice.getTitle();

        if(newNotice.getContents().length()>100) {
            tmpContent=tmpContent.substring(0, 100);
        }

        if(newNotice.getEmail().length()>100){
            tmpEmail=tmpEmail.substring(0, 100);
        }

        if(newNotice.getTitle().length()>100){
            tmpTitle=tmpTitle.substring(0, 100);
        }

        newNotice.setContents(ConverterStringToHtml(tmpContent));
        newNotice.setEmail(ConverterStringToHtml(tmpEmail));
        newNotice.setTitle(ConverterStringToHtml(tmpTitle));
        noticeDao.update(id,newNotice);
    }
    private String ConverterStringToHtml(String str){
        return str.replaceAll("[^\\w\\s\\p{L}]", "").replaceAll("\\n", "<br>");
    }

    private String ConverterHtmlToStr(String str){
        return str.replaceAll("<br>", "\n");
    }
    private String ConverterStringToHtmlEnter(String str){
        return str.replaceAll("\n", "<br>");
    }
}
