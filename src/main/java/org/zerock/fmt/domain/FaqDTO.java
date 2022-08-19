package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaqDTO {
	
	private Integer faq_number;
	private String ad_id;
	private String faq_title;
	private String faq_content;

} // end class
