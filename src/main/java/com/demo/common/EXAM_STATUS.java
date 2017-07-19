package com.demo.common;

public enum EXAM_STATUS {
	NEW(1), PROGRESS(2), COMPLETED(3), REJECTED(4);

	private int id;

	private EXAM_STATUS(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static EXAM_STATUS getById(int id) {
		for (EXAM_STATUS e : EXAM_STATUS.values()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
}
