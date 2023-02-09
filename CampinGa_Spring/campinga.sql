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
update businessman set image='Daejeon01.png' where bseq=1;
update businessman set image='Daejeon02.png' where bseq=2;
update businessman set image='Daejeon03.png' where bseq=3;
update businessman set image='Daejeon04.png' where bseq=4;
update businessman set image='Daejeon05.png' where bseq=5;
update businessman set image='Daejeon06.png' where bseq=6;
update businessman set image='Seoul01.png' where bseq=7;
update businessman set image='Seoul02.png' where bseq=8;
commit;
select * from businessman;