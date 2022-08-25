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
	@Select("SELECT * FROM test_file WHERE F_NO= #{fNo}")
	public abstract FileVO selectFile(@Param("fNo")int fNO) throws DAOException;
}//end 
