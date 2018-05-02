package br.com.workmade.helpdesk.HelpDesk.enums;

public enum StatusEnum {
	NEW,
	ASSIGNED,
	SOLVED,
	APPROVED,
	DESAPPROVED,
	CLOSED;

	public static StatusEnum getStatus(String status) {
		switch (status) {
		case "NEW":return NEW;
		case "ASSIGNED":return ASSIGNED;
		case "SOLVED":return SOLVED;
		case "APPROVED":return APPROVED;
		case "DESAPPROVED":return DESAPPROVED;
		case "CLOSED":return CLOSED;
		default: return NEW;
		}
	}

	
}
