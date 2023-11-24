import { localAxios } from '@/util/http-commons';
import { imgbbAxios } from '@/util/http-commons';

const local = localAxios();
const imgbb = imgbbAxios();

const url = '/user';
const profileurl = '/profile';

async function userConfirm(param, success, fail) {
  console.log('param', param);
  await local
    .post(`/user/login`, JSON.stringify(param))
    .then(success)
    .catch(fail);
  console.log('userConfirm ok');
}

async function findById(userid, success, fail) {
  local.defaults.headers['Authorization'] =
    sessionStorage.getItem('accessToken');
  await local.get(`${url}/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers['refreshToken'] =
    sessionStorage.getItem('refreshToken'); //axios header에 refresh-token 셋팅
  await local.post(`${url}/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`${url}/logout/${userid}`).then(success).catch(fail);
}

function joinMember(member, success, fail) {
  console.log('member.js member', member);
  local.post(`${url}/register`, member).then(success).catch(fail);
}

function idCheck(userId, success, fail) {
  console.log('idCheck', userId);
  local.get(`${url}`, { params: { userId } }).then(success).catch(fail);
}

function updateMember(member, success, fail) {
  local.put(`${url}`, JSON.stringify(member)).then(success).catch(fail);
}

function deleteMember(userId, commentNo, success, fail) {
  local.delete(`${url}/${userId}`).then(success).catch(fail);
}

// 이미지 관련
function uploadImage(formdata, success, fail) {
  console.log('sendImgURL......{}', formdata);
  imgbb.post(``, formdata).then(success).catch(fail);
}

function insertImg(imgdata, success, fail) {
  console.log('insertImg......{}', imgdata);
  local.post(`${profileurl}`, imgdata).then(success).catch(fail);
}

function getImg(userId, success, fail) {
  console.log('getImg......{}', userId);
  local.get(`${profileurl}/${userId}`).then(success).catch(fail);
}
function updateImg(imgdata, success, fail) {
  console.log('updateImg......{}', imgdata);
  local.put(`${profileurl}`, imgdata).then(success).catch(fail);
}

export {
  userConfirm,
  findById,
  tokenRegeneration,
  logout,
  idCheck,
  joinMember,
  updateMember,
  deleteMember,
  uploadImage,
  insertImg,
  getImg,
  updateImg
};
