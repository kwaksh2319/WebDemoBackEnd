package kr.co.kshproject.webDemo.Applicaiton.Comment;

import kr.co.kshproject.webDemo.Domain.Comment.Comment;
import kr.co.kshproject.webDemo.Domain.Comment.CommentRepository;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeCustomRepository;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import kr.co.kshproject.webDemo.Domain.Users.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final CommentRepository commentRepository;
    private final NoticeCustomRepository noticeCustomRepository;

    private final UsersRepository  usersRepository;

    @Override
    public Comment save(int page, Long noticeId, Long userId, Comment comment) {
        LocalDate now = LocalDate.now();
        Optional<Notice> notice =noticeCustomRepository.findWithCommentsById(page,noticeId);
        Notice tmpNotice= notice.get();

        Optional<Users> user =usersRepository.findById(userId);

        comment.setUserName(user.get().getName());// 유저변경할것
        comment.setNotice(tmpNotice);
        comment.setCreatedDate(now.toString());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment update(Long id, Comment saveComment) {
        Optional<Comment> findComment =commentRepository.findById(id);
        if(findComment.isPresent()==false){
            return null;
        }
        Comment comment=findComment.get();
        comment.setContents(saveComment.getContents());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        commentRepository.deleteAll();
    }
}
