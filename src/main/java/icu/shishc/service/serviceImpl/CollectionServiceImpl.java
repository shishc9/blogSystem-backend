package icu.shishc.service.serviceImpl;

import icu.shishc.mapper.CollectionMapper;
import icu.shishc.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2021-5-26, 10:26
 * @author: ShiShc
 */

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionMapper collectionMapper;

    @Override
    public Integer deleteBlogCollection(Long bid) {
        return collectionMapper.deleteBlogCollection(bid);
    }
}
