package kr.co.kshproject.webDemo.Applicaiton.Comment;

import kr.co.kshproject.webDemo.Applicaiton.Notice.NoticeService;
import kr.co.kshproject.webDemo.Applicaiton.User.UserService;
import kr.co.kshproject.webDemo.Domain.Comment.Comment;
import kr.co.kshproject.webDemo.Domain.Comment.CommentCustomRepository;
import kr.co.kshproject.webDemo.Domain.Comment.CommentDTO;
import kr.co.kshproject.webDemo.Domain.Comment.CommentRepository;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final CommentCustomRepository commentCustomRepository;
    private final NoticeService noticeService;
    private final UserService userService;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              CommentCustomRepository commentCustomRepository,
                              NoticeService noticeService,
                              UserService userService
    ){
        this.commentRepository=commentRepository;
        this.commentCustomRepository=commentCustomRepository;
        this.noticeService=noticeService;
        this.userService=userService;
    }

    @Override
    public Comment save(CommentDTO commentDTO) {
        Comment comment=new Comment(commentDTO);
        return commentRepository.save(comment);
    }

    @Override
    public List<CommentDTO> findAll() {
        return commentCustomRepository.findAll();
    }

    @Override
    public Map<String, List> findAll(int page, int size) {
        return commentCustomRepository.findAll(page,size);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentCustomRepository.findById(id);
    }

    @Override
    public Comment update(Long id, CommentDTO commentDTO) {
        return commentRepository.save( ConverEntity(id,commentDTO) );
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        commentRepository.deleteAll();
    }

    private Comment ConverEntity(Long id, CommentDTO commentDTO){

        Optional<Comment> comment=commentCustomRepository.findById(id);
        Long noticeId=commentDTO.getNoticeId();
        Long userId=commentDTO.getUserId();

        Optional<Notice> updateNotice =noticeService.findById(noticeId);
        Optional<Users> updateUsers =userService.findById(userId);

        comment.get().setUserName(commentDTO.getUserName());
        comment.get().setContents(commentDTO.getContents());
        comment.get().setCreatedDate(commentDTO.getCreatedDate());

        if(updateNotice.isPresent()==true){
            comment.get().setNotice(updateNotice.get());
        }
        if(updateUsers.isPresent()==true){
            comment.get().setUsers(updateUsers.get());
        }

        return comment.get();
    }
}
