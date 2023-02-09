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

select * from businessman;