package com.github.fppt.jedismock.operations;

import com.github.fppt.jedismock.server.Response;
import com.github.fppt.jedismock.server.Slice;
import com.github.fppt.jedismock.storage.RedisBase;

import java.util.List;

class RO_hset extends AbstractRedisOperation {
    RO_hset(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice hsetValue(Slice key1, Slice key2, Slice value){
        Slice foundValue = base().getValue(key1, key2);
        base().putValue(key1, key2, value, -1L);
        return foundValue;
    }

    Slice response() {
        Slice key1 = params().get(0);

        int adds=0;
        for (int i = 1; i < params().size(); i+=2) {
            Slice key2 = params().get(i);
            Slice value = params().get(i+1);
            Slice oldValue = hsetValue(key1, key2, value);

            if(oldValue==null){
                adds+=1;
            }
        }

        return Response.integer(adds);
    }
}
