package com.xinho.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关于 BaseBean ：
 *  1. 分页功能需要每个DomainBean携带页面信息，显然，每个DomainBean手写一个Page对象是不现实的，而且视觉上也容易造成混淆
 *  2. BaseBean还可以统一为DomainBean扩充很多东西，看起来现在是用处不那么大的样子，但一旦项目变大，需求要改，那么就很有用了。
 *  3. “开发一时爽，维护两行泪”。看问题要长远，解耦和聚合，这就是一个小小的 BaseBean 所体现出来的。
 */
@Data public class BaseBean {
    private Page page;

    BaseBean(){
        this.page = new Page();
    }
}
