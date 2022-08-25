package org.zerock.fmt.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.FileDTO;
import org.zerock.fmt.domain.FileVO;
import org.zerock.fmt.exception.DAOException;


public interface FileMapperTest {

	//파일등록
	public abstract Integer insertFile(FileDTO file) throws DAOException;
	
	//파일조회
	@Select("SELECT * FROM tbl_tt_file WHERE user_email = #{user_eamil}")
	public abstract FileVO selectFile(@Param("user_eamil")String user_email) throws DAOException;
}//end 
