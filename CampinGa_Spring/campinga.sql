-- 신규/추천 캠핑장
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


-- 메인에서 캠핑장 검색
create or replace procedure getSearchResult(
    p_address1 in businessman.caddress1%type, 
    p_address2 in businessman.caddress2%type, 
    p_cname in businessman.caddress3%type,
    p_cur out SYS_REFCURSOR
)
IS
BEGIN
    open p_cur for
        select * from businessman 
        where caddress1 like '%'||p_address1||'%' 
            and caddress2 like '%'||p_address2||'%' 
            and cname like '%'||p_cname||'%';
END;

-- 02-08
-- 카테고리 : ALL
create or replace PROCEDURE categoryAll(
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR    
        SELECT bseq, cname, category, facilities, caddress1, caddress2, caddress3, phone, image
        FROM businessman;
END;

-- 카테고리 조회
create or replace PROCEDURE categoryList(
    p_cate IN businessman.category%type,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR    
        SELECT bseq, cname, category, facilities, caddress1, caddress2, caddress3, phone, image
        FROM businessman where category=p_cate;
END;



-- 02-09
-- 캠핑장 이름 검색
CREATE OR REPLACE PROCEDURE campNameSerch(
    p_name IN businessman.cname%type,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR    
        SELECT bseq, cname, category, facilities, caddress1, caddress2, caddress3, phone, image
        FROM businessman where cname LIKE '%'||p_name||'%';
END;


-- bseq로 캠핑장 정보 검색
CREATE OR REPLACE PROCEDURE campDetailByBseq(
    p_bseq IN businessman.bseq%type,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR    
        SELECT cname, phone, email, caddress1, caddress2, caddress3, facilities, image, content, category
        FROM businessman where bseq=p_bseq;
END;


-- 즐겨찾기 조회 ()

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
create or replace PROCEDURE selectCampOne(
    p_cseq IN camping_view.cseq%type,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR 
    SELECT * FROM camping_view WHERE cseq=p_cseq;
END;

-- bseq로 캠핑장 객실 리스트 검색
CREATE OR REPLACE PROCEDURE selectCampingList(
    p_bseq IN businessman.bseq%type,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR    
        SELECT * FROM camping_view where bseq=p_bseq;
END;


-- 캠핑장 qna, review count조회
CREATE OR REPLACE PROCEDURE getCount(
    p_tableName IN number,
    p_bseq IN businessman.bseq%type,
    p_cnt OUT number
)
IS
    v_cnt NUMBER;
BEGIN
    IF p_tableName = 1 THEN
        SELECT COUNT(*) INTO v_cnt FROM camp_qna WHERE bseq=p_bseq;
    ELSIF p_tableName = 2 THEN
        SELECT COUNT(*) INTO v_cnt FROM review WHERE  bseq=p_bseq;
    END IF;
    p_cnt := v_cnt;
END;



-- 캠핑장 qna 조회
CREATE OR REPLACE PROCEDURE selectQnaByBseq(
    p_bseq IN businessman.bseq%type,
    p_startNum IN number,
    p_endNum IN number,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
   OPEN p_rc FOR
        SELECT * FROM (
            SELECT * FROM (
                SELECT ROWNUM AS rn, q.*FROM 
                    ( ( SELECT * FROM camp_qna WHERE bseq=p_bseq ORDER BY qseq DESC)q)
            ) WHERE rn >= p_startNum
        ) WHERE rn <= p_endNum;
END;



-- 캠핑장 review 조회
CREATE OR REPLACE PROCEDURE selectReviewByBseq(
    p_bseq IN businessman.bseq%type,
    p_startNum IN number,
    p_endNum IN number,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
   OPEN p_rc FOR
        SELECT * FROM (
            SELECT * FROM (
                SELECT ROWNUM AS rn, r.*FROM 
                    ( ( SELECT * FROM review WHERE bseq=p_bseq ORDER BY rseq DESC)r)
            ) WHERE rn >= p_startNum
        ) WHERE rn <= p_endNum;
END;

-- 멤버 조회
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

-- 멤버 회원가입 등록
CREATE OR REPLACE PROCEDURE insertMemberCam(
    p_mid IN member.mid%type,
    p_pwd IN member.pwd%type,
    p_name IN member.name%type,
    p_email IN member.email%type,
    p_phone IN member.phone%type
)
IS
BEGIN
    insert into member ( mid, pwd, name, email, phone)
    values( p_mid, p_pwd, p_name, p_email, p_phone);
    commit;
END;




-- 02-10
-- 관리자 조회
CREATE OR REPLACE  PROCEDURE getAdminList(
    p_aid IN admin.aid%type,
    p_rc OUT SYS_REFCURSOR 
)
IS
BEGIN
        OPEN p_rc FOR
            select * from admin where aid=p_aid;
END;



-- 관리자 count
CREATE OR REPLACE  PROCEDURE AdminCount(
    p_tableName IN number,
    p_cnt OUT number
)
IS
    v_cnt NUMBER;
BEGIN
    IF p_tableName = 1 THEN
        SELECT COUNT(*) INTO v_cnt FROM member;
    ELSIF p_tableName = 2 THEN
        SELECT COUNT(*) INTO v_cnt FROM businessman;
    ELSIF p_tableName = 3 THEN
        SELECT COUNT(*) INTO v_cnt FROM RESERVATE_VIEW;
    ELSIF p_tableName = 4 THEN
        SELECT COUNT(*) INTO v_cnt FROM review;
    ELSIF p_tableName = 5 THEN
        SELECT COUNT(*) INTO v_cnt FROM notice;
    END IF;
    p_cnt := v_cnt;
END;



-- 관리자 : 회원 조회
CREATE OR REPLACE  PROCEDURE adminMemberList(
    p_startNum IN number,
    p_endNum IN number,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM (
            SELECT * FROM (
                SELECT ROWNUM AS rn, m.*FROM 
                    ( ( SELECT * FROM member order by mid)m)
            ) WHERE rn >= p_startNum
        ) WHERE rn <= p_endNum;
END;


--  관리자 :  캠핑장 관리 조회 
CREATE OR REPLACE  PROCEDURE adminCampingList(
    p_startNum IN number,
    p_endNum IN number,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM (
            SELECT * FROM (
                SELECT ROWNUM AS rn, m.*FROM 
                    ( ( SELECT * FROM businessman order by bseq desc)m)
            ) WHERE rn >= p_startNum
        ) WHERE rn <= p_endNum;
END;








