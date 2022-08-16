package org.zerock.fmt.domain;

import lombok.Value;

@Value
public class FaqVO {
	
	private Integer faq_number;
	private String ad_id;
	private String faq_title;
	private String faq_content;

} // end class
