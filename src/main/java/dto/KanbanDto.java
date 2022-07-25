package dto;

import java.time.LocalDateTime;

import constants.Status;

/**
 * KanbanDto (Data Transfer Object)
 * 
 * @author YONGGYO
 *
 */
public class KanbanDto {

	private int id; // 작업 번호
	private Status status; // 작업 상태 
	private String workNm; // 작업 내용
	private LocalDateTime regDt; // 등록일시
	private LocalDateTime modDt; // 수정일시
	
	public KanbanDto() {}
	
	public KanbanDto(Status status, String workNm) {
		super();
		this.status = status;
		this.workNm = workNm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getWorkNm() {
		return workNm;
	}
	
	public void setWorkNm(String workNm) {
		this.workNm = workNm;
	}
	
	public LocalDateTime getRegDt() {
		return regDt;
	}

	public void setRegDt(LocalDateTime regDt) {
		this.regDt = regDt;
	}

	public LocalDateTime getModDt() {
		return modDt;
	}

	public void setModDt(LocalDateTime modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "KanbanDto [id=" + id + ", status=" + status + ", workNm=" + workNm + ", regDt=" + regDt + ", modDt="
				+ modDt + "]";
	}
}
