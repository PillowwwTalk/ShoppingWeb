package com.zhj.pojo;
import java.util.LinkedHashMap;
import java.util.Map;

public class History {
    private Map<Integer,HistoryItem> items = new LinkedHashMap<Integer,HistoryItem>();

    public Map<Integer, HistoryItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, HistoryItem> items) {
        this.items = items;
    }

    /**
     * 添加商品项
     *
     * @param historyItem
     */
    public void addItem(HistoryItem historyItem) {
        // 先查看购物车中是否已经添加过此商品，如果已添加，则数量累加，总金额更新，如果没有添加过，直接放到集合中即可
        HistoryItem item = items.get(historyItem.getId());

        if (item == null) {
            // 之前没添加过此商品
            items.put(historyItem.getId(), historyItem);
        } else {
        }
    }

    /**
     * 删除商品项
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }
    public void clear() {
        items.clear();
    }
}
