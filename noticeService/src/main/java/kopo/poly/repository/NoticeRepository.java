package kopo.poly.repository;

import kopo.poly.repository.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    List<NoticeEntity> findAllByOrderByNoticeSeqDesc();

    NoticeEntity findByNoticeSeq(Long noticeSeq);

    @Modifying(clearAutomatically = true)
    @Query(value =
            "UPDATE NOTICE A SET A.readCnt = IFNULL(A.readCnt, 0) + 1 " +
                    "WHERE A.noticeSeq = :noticeSeq",
            nativeQuery=true)
    int updateReadCnt(@Param("noticeSeq") Long noticeSeq);


}
