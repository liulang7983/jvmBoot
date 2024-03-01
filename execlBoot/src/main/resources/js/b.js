const puppeteer = require('puppeteer');
async function getPDF() {
  const browser = await puppeteer.launch({
    headless: 'new',
    args: [
      "--no-sandbox", // linux系统中必须开启
      "--no-zygote",
      // "--single-process", // 此处关掉单进程
      "--disable-setuid-sandbox",
      "--disable-gpu",
      "--disable-dev-shm-usage",
      "--no-first-run",
      "--disable-extensions",
      "--disable-file-system",
      "--disable-background-networking",
      "--disable-default-apps",
      "--disable-sync", // 禁止同步
      "--disable-translate",
      "--hide-scrollbars",
      "--metrics-recording-only",
      "--mute-audio",
      "--safebrowsing-disable-auto-update",
      "--ignore-certificate-errors",
      "--ignore-ssl-errors",
      "--ignore-certificate-errors-spki-list",
      "--font-render-hinting=medium",
    ]
  });
  // try...catch...
  try {
    const page = await browser.newPage();
    // 页眉模板（图片使用base64，此处的src的base64为占位值）
    const headerTemplate = `<div
style="width: calc(100% - 28px); margin-top: -13px; font-size:8px;border-bottom:2px solid #e1dafb;padding:6px 14px;display: flex; justify-content: space-between; align-items:center;">
<span style="color: #9a7ff7; font-size: 12px; font-family: my-font;">李钟航的报告模板</span>
<img style="width: 80px; height: auto;" src="https://img-blog.csdnimg.cn/2022010702200041286.png" />
</div>`
    // 页脚模板（pageNumber处会自动注入当前页码）
    const footerTemplate = `<div 
style="width:calc(100% - 28px);margin-bottom: -20px; font-size:8px; padding:15px 14px;display: flex; justify-content: space-between; ">
<span style="color: #9a7ff7; font-size: 10px;">这里是页脚文字</span>
<span style="color: #9a7ff7; font-size: 13px;" class="pageNumber"></span> 
</div>`;
    // 对于大的PDF生成，可能会时间很久，这里规定不会进行超时处理
    await page.setDefaultNavigationTimeout(0);
    // 定义html内容
    // await page.setContent(this.HTMLStr, { waitUntil: "networkidle2" });
    // 等待字体加载响应
    // await page.evaluateHandle("document.fonts.ready");
    await page.goto(`http://172.18.26.250:7000/sso?token=sso:2c6c3e4f9c7ee3cbfd6f4c42e70c56edfc0189b9c84d8eb567c30158fc7abb8d92e731533f261bd3493594aeaa1a3b3aecd137df6fbcc1ce53e7d9eaba2a1238c1353fb0842019a08616d17e8b6b0f3eb957ca27c742e9ab5fd15021cb0f662a&userid=JARVIS&redirect_uri=/bsReport&batchId=179020&names=成都东根集团有限公司&endDate=2019-03-27&startDate=2019-03-19&tpId=5bf51068654d491fbb0d59b2a8135317&reportTitle=wjwtest-(贷款人)-异常流水金额参数校验`, { waitUntil: 'networkidle2' });
    await page.pdf({
      path: 'example11.pdf',
      // 页面缩放比例
      scale: 1,
      // 是否展示页眉页脚
      displayHeaderFooter: true,
      // pdf存储单页大小
      format: "a4",
      // 页面的边距
      // 页眉的模板
      headerTemplate,
      // 页脚的模板
      footerTemplate,
      margin: {
        top: 50,
        bottom: 50,
        left: 0,
        right: 0
      },
      // 输出的页码范围
      pageRanges: "",
      // CSS
      preferCSSPageSize: true,
      // 开启渲染背景色，因为 puppeteer 是基于 chrome 浏览器的，浏览器为了打印节省油墨，默认是不导出背景图及背景色的
      // 坑点，必须加
      printBackground: true,
    });
    // 关闭browser
    await browser.close();
    // 返回的是buffer不需要存储为pdf，直接将buffer传回前端进行下载，提高处理速度
    // return pdfbuf
  } catch (e) {
    await browser.close();
    throw e
  }
}
getPDF()
