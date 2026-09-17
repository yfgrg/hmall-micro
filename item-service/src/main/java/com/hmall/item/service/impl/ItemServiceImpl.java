package com.hmall.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.api.item.dto.ItemDTO;
import com.hmall.api.item.dto.OrderDetailDTO;
import com.hmall.common.exception.BizIllegalException;
import com.hmall.common.utils.BeanUtils;

import com.hmall.item.domain.po.Item;
import com.hmall.item.mapper.ItemMapper;
import com.hmall.item.service.IItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 虎哥
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Transactional
    public void deductStock(List<OrderDetailDTO> items) {
        for (OrderDetailDTO item : items) {
            int i = baseMapper.updateStock(item);
            if(i<=0){
                throw new BizIllegalException("库存不足！");
            }
        }
    }

    @Override
    @Transactional
    public void restoreStock(List<OrderDetailDTO> items) {
        for (OrderDetailDTO item : items) {
            int count = baseMapper.restoreStock(item);
            if (count <= 0) {
                throw new BizIllegalException("恢复库存失败，商品不存在！");
            }
        }
    }

    @Override
    public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
        return BeanUtils.copyList(listByIds(ids), ItemDTO.class);
    }
}
