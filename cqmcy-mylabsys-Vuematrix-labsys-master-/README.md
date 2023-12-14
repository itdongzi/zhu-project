## 开发

```bash saf
# 克隆项目
git clone http://www.cqmcy.cn:3000/cqcmc/Vuematrix-labsys.git

# 进入项目目录
cd vuematrix-labsys-master

# 安装依赖
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npmmirror.com

# 启动服务
npm run dev
```

浏览器访问 http://localhost:8092

## 发布

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod
```

#vue-layer http://layer.zuoyanit.com/