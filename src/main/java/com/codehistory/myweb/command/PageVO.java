package com.codehistory.myweb.command;

import java.util.ArrayList;
import java.util.List;

import com.codehistory.myweb.util.Criteria;

import lombok.Data;

@Data
public class PageVO {

	//페이지네이션을 계산하는 클래스
	private int start;//첫페이지번호
	private int end;//끝페이지번호
	private boolean prev;//이전버튼
	private boolean next;//다음버튼
	
	private int page;//조회하는번호
	private int amount;//데이터개수
	private int total;//전체게시글수
	
	private int realEnd;//실제끝번호
	private List<Integer> pageList; //화면에 반복시킬 페이지 번호. (thymeleaf에서는 향상된 for문을 사용하기 때문에 list로 페이지번호 생성)
	
	private Criteria cri;
	private int pageCnt=10;
	
	public PageVO(Criteria cri, int total) {
		
		this.page=cri.getPage();
		this.amount=cri.getAmount();
		this.total=total;
		this.cri=cri;
		
		//끝페이지 번호 - (int)Math.ceil(조회하는페이지번호/페이지네이션개수)*페이지네이션개수
		this.end=(int)Math.ceil(this.page/(double)pageCnt)*pageCnt;
		
		//시작페이지번호-끝페이지번호 - 페이지네이션개수 +1
		this.start=this.end-pageCnt+1;
		
		//실제끝번호
		//(int)Math.ceil(전체게시글 수/데이터개수)
		this.realEnd=(int)Math.ceil(total/(double)this.amount);
		
		//실제끝번호 재계산
		//데이터가 141개라면->1~10번 페이지는 10번, 11~20페이지는 15번
		this.end=this.end>this.realEnd?this.realEnd:this.end;
		
		//이전버튼 활성화
		//start는 1, 11, 21 .....
		this.prev=this.start>1;
		
		//다음버튼 활성화
		this.next=this.realEnd>this.end;
		
		//화면에서 반복시킬 페이지데이터
		this.pageList=new ArrayList<>();
		for(int i=this.start;i<=this.end;i++) {
			pageList.add(i);
		}
		
	}
	
	
}
