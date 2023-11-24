import { localAxios } from '@/util/http-commons';

const local = localAxios(); // axios instance

const url = '/board';
function listArticle(param, success, fail) {
  console.log('난 board.js임');
  local.get(`${url}`,{params: param}).then(success).catch(fail);
}

function listMyArticle(userId, success, fail) {
  console.log('난 listMyArticle임', userId);
  local.get(`${url}/list/${userId}`).then(success).catch(fail);
}

function detailArticle(articleno, success, fail) {
  local.get(`${url}/${articleno}`).then(success).catch(fail);
}

function registArticle(article, success, fail) {
  console.log('boardjs article', article);
  local.post(`${url}`, JSON.stringify(article)).then(success).catch(fail);
}

function getModifyArticle(articleno, success, fail) {
  local.get(`${url}/modify/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  local.put(`${url}`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  local.delete(`${url}/${articleno}`).then(success).catch(fail);
}

export {
  listArticle,
  listMyArticle,
  detailArticle,
  registArticle,
  getModifyArticle,
  modifyArticle,
  deleteArticle,
};
