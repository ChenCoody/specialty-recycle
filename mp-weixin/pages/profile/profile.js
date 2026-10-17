const app = getApp();
import { listByUserId, deleteGoods } from "../../api/goods"

Page({
  data: { user: {}, goodsList: [] },
  onShow() { this.init(); },
  onPullDownRefresh() { this.init(); },
  async init() {
    const user = wx.getStorageSync("user") || {};
    if (!user.id) {
      wx.stopPullDownRefresh();
      wx.navigateTo({ url: "/pages/login/login" });
      return;
    }
    try {
      const result = await listByUserId();
      this.setData({ user, goodsList: result.data || [] });
    } finally {
      wx.stopPullDownRefresh();
    }
  },
  deleteGoods(e) {
    const index = e.currentTarget.dataset.index;
    const item = this.data.goodsList[index];
    wx.showModal({
      title: "删除商品",
      content: `确定删除“${item.title}”吗？`,
      success: async result => {
        if (!result.confirm) return;
        const response = await deleteGoods({ id: item.id });
        app.handleRes(response, "", () => {
          const goodsList = this.data.goodsList.slice();
          goodsList.splice(index, 1);
          this.setData({ goodsList });
        });
      }
    });
  },
  logout() {
    wx.showModal({
      title: "退出登录",
      content: "确定退出当前账号吗？",
      success: result => {
        if (result.confirm) {
          wx.clearStorageSync();
          this.setData({ user: {}, goodsList: [] });
          wx.switchTab({ url: "/pages/index/index" });
        }
      }
    });
  }
});
