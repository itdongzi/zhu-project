<template>
  <div>
    <el-container>
      <el-header height="80px">
        <div class="wrapbox">
          <el-row>
            <el-col :span="12">
              <div style="padding-top: 10px">
                <img :src="logoImg" height="60" />
              </div>
            </el-col>
            <el-col :span="12">
              <div style="line-height: 60px; text-align: right">
                <el-link href="#" :underline="false" target="_blank"
                  >学院主页</el-link
                ><el-divider direction="vertical"></el-divider>
                <el-link href="#" :underline="false" target="_blank"
                  >使用文档</el-link
                ><el-divider direction="vertical"></el-divider>
                <el-button>用户注册</el-button>
                <el-button type="primary">平台登录</el-button>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-header>
      <el-main>
        <div style="background-color: #f2f6fc">
          <div class="wrapbox">
            <el-carousel height="360px" interval="5000">
              <el-carousel-item v-for="item in 4" :key="item">
                <h3 class="small">{{ item }}</h3>
              </el-carousel-item>
            </el-carousel>
          </div>
        </div>
        <div style="background-color: white; padding: 36px 0">
          <div class="wrapbox">
            <el-tabs value="first">
              <el-tab-pane label="新手云游指南" name="first">
                <el-row>
                  <el-col
                    :span="4"
                    v-for="(o,index) in 7"
                    :key="o"
                    style="padding:10px;"
                  >
                    <el-card shadow="hover">
                      <img
                        src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                        width="140" height="140"
                      />
                      <div style="padding: 14px;text-align:center">
                        <el-link href="https://element.eleme.io" :underline="false" target="_blank">默认链接{{index}}</el-link>
                      </div>
                    </el-card>
                  </el-col>
                </el-row>
              </el-tab-pane>
            </el-tabs>
          </div>
        </div>
        <div style="background-color: #f2f6fc; padding: 36px 0">
          <div class="wrapbox">
            <el-tabs value="first">
              <el-tab-pane label="新手云游指南" name="first">
                <p>fasdfadsfdsafasf</p>
              </el-tab-pane>
            </el-tabs>
          </div>
        </div>
        <div style="background-color: white; padding: 36px 0">
          <div class="wrapbox">
            <el-tabs value="first">
              <el-tab-pane label="新手云游指南" name="first">
                <p>fasdfadsfdsafasf</p>
              </el-tab-pane>
            </el-tabs>
          </div>
        </div>
      </el-main>
      <el-footer height="300px">
        <div class="wrapbox" style="padding-top: 50px">
          <el-row>
            <el-col :span="12">
              <h3>实训室管理平台</h3>
              <div>Copyright © 2012-2023 UCloud</div>
              <div>优刻得科技股份有限公司</div>
              <div>Tel：4000188113</div>
            </el-col>
            <el-col :span="4"><p>UCloud与云服务</p></el-col>
            <el-col :span="4"><p>快速入口</p></el-col>
            <el-col :span="4"><p>常见问题</p></el-col>
          </el-row>
          <el-row>
            <el-col :span="24"
              ><p>沪公网安备 31011002000058号 | 沪ICP备12020087号-3</p></el-col
            >
          </el-row>
        </div></el-footer
      >
    </el-container>
  </div>
</template>

<script>
import logoImg from "@/assets/logo/logo-full.gif";

export default {
  name: "Index",
  data() {
    return {
      logoImg: logoImg,
    };
  },
};
</script>

<style rel="stylesheet/scss" lang="scss">
.wrapbox {
  margin: 0 auto;
  padding: 0;
  width: 1200px;
}

.el-header {
  color: #333;
  text-align: left;
  border-bottom: #dcdfe6 1px solid;
}

.el-main {
  padding: 0;
  color: #333;
}

.el-footer {
  background-color: #303133;
  color: #c0c4cc;
  text-align: left;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 28px;
  opacity: 0.75;
  line-height: 360px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>