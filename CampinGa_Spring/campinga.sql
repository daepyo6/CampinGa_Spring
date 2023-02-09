create or replace procedure getNewRecoCamping(
    p_cur1 out SYS_REFCURSOR,
    p_cur2 out SYS_REFCURSOR
)
IS
BEGIN
    open p_cur1 for
        select bseq, cname, content, image 
        from (select * from businessman order by bseq desc) where rownum<=4;
    open p_cur2 for
        select cname, image, bseq 
        from businessman where rownum<=8;
END;


CREATE OR REPLACE PROCEDURE getMemberCam(
    p_mid IN member.mid%type,
    p_curvar OUT SYS_REFCURSOR
)
IS
    result_cur SYS_REFCURSOR;
BEGIN
    OPEN result_cur FOR SELECT * FROM member WHERE mid= p_mid;
        p_curvar := result_cur;
END;




CREATE OR REPLACE PROCEDURE insertMemberCam(
    p_mid IN member.mid%type,
    p_pwd IN member.pwd%type,
    p_name IN member.name%type,
    p_email IN member.email%type,
    p_mphone IN member.mphone%type
)
IS
BEGIN
    insert into member ( mid, pwd, name, email, mphone)
    values( p_mid, p_pwd, p_name, p_email, p_mphone);
    commit;
END;

select*from camping;



select * from businessman;

-- 즐겨찾기 조회(예외처리 추가)
CREATE OR REPLACE PROCEDURE getFav(
    p_mid IN member.mid%type,
    p_bseq IN businessman.bseq%type,
    p_fav OUT favorites.fav_check%type
)
IS
    c_fav favorites.fav_check%type;
BEGIN
    SELECT fav_check INTO c_fav FROM favorites 
    WHERE mid=p_mid and bseq=p_bseq;
    p_fav := c_fav;
EXCEPTION 
    when no_data_found then p_fav := 'n';
END;



-- 캠핑장 객실 한개 조회
CREATE OR REPLACE PROCEDURE selectCampOne(
    p_cseq IN camping_view.cseq%type,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR 
    SELECT * FROM camping_view WHERE cseq=p_cseq;
END;



select*from reservation;








