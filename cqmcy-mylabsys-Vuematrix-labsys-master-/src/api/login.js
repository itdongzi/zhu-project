import request from '@/utils/request'

// 登录方法
export function login(username, password, textcode, uuid) {
  const data = {
    username,
    password,
    textcode,
    uuid
  }
  return request({
    url: '/web/login',
    headers: {
      isToken: false
    },
    method: 'post',
    data: data
  })
} 

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/web/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/web/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/web/codeImage',
    headers: {
      isToken: false
    },
    method: 'get',
    timeout: 20000
  })
}