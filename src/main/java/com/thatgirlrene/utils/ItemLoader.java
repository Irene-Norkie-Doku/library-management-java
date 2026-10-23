package com.thatgirlrene.utils;

import com.thatgirlrene.exception.BorrowLimitExceededException;
import com.thatgirlrene.exception.ItemNotAvailableException;
import com.thatgirlrene.model.LibraryItem;
import java.util.List;
import java.io.IOException;


public interface ItemLoader {
    List<LibraryItem> loadItems(String path) throws  IOException;
}
