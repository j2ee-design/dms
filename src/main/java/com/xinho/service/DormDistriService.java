package com.xinho.service;

import java.util.List;

public interface DormDistriService {

    int distrbuteBatch(List<Integer> stuIds, List<Integer> dormIds);

    int delSingle(Integer stuId);
}
