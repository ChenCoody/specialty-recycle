import { insertGoods } from "../../api/goods"
import { uploadImage } from "../../api/upload"

const app = getApp();
Page({
  data: { form: { title: "旧鞋旧包", content: "大量的旧包旧鞋，5成新。", estimatedWeight: "", address: "", contactMobile: "" }, imageList: [] },
  onShow() {
    const user = wx.getStorageSync("user");
    if (!user || !user.id) wx.navigateTo({ url: "/pages/login/login" });
  },
  inputTitle(e) { this.setData({ "form.title": e.detail.value }); },
  editorReady() {
    this.createSelectorQuery().select("#editor").context(result => {
      if (result) result.context.setContents({ html: this.data.form.content });
    }).exec();
  },
  inputContent(e) { this.setData({ "form.content": e.detail.html }); },
  inputEstimatedWeight(e) { this.setData({ "form.estimatedWeight": e.detail.value }); },
  inputAddress(e) { this.setData({ "form.address": e.detail.value }); },
  inputContactMobile(e) { this.setData({ "form.contactMobile": e.detail.value }); },
  chooseImage() {
    wx.chooseMedia({
      count: 9 - this.data.imageList.length,
      mediaType: ["image"],
      success: ({ tempFiles }) => this.setData({ imageList: this.data.imageList.concat(tempFiles.map(item => item.tempFilePath)) })
    });
  },
  removeImage(e) {
    const imageList = this.data.imageList.slice();
    imageList.splice(e.currentTarget.dataset.index, 1);
    this.setData({ imageList });
  },
  clearForm() {
    this.createSelectorQuery().select("#editor").context(result => {
      if (result) result.context.setContents({ html: "大量的旧包旧鞋，5成新。" });
    }).exec();
    this.setData({ form: { title: "旧鞋旧包", content: "大量的旧包旧鞋，5成新。", estimatedWeight: "", address: "", contactMobile: "" }, imageList: [] });
  },
  async creationSubmit() {
    const { form, imageList } = this.data;
    if (!imageList.length) return wx.showToast({ title: "请至少上传一张商品图片", icon: "none" });
    if (!form.title || !form.content || !form.estimatedWeight || !form.address || !form.contactMobile) return wx.showToast({ title: "请填写完整的商品信息", icon: "none" });
    if (!/^1\d{10}$/.test(form.contactMobile)) return wx.showToast({ title: "请输入正确的手机号", icon: "none" });
    const imagesUrl = [];
    for (let i = 0; i < imageList.length; i++) {
      const result = await uploadImage({ path: imageList[i] });
      if (result.status === 200) imagesUrl.push(result.data);
    }
    if (!imagesUrl.length) return wx.showToast({ title: "图片上传失败，请重试", icon: "none" });
    const result = await insertGoods({ ...form, images: imagesUrl.join(";") });
    app.handleRes(result, "", () => { this.clearForm(); wx.showToast({ title: "商品发表成功", icon: "success" }); });
  }
});
