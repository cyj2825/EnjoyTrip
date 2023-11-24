import { localAxios } from "@/util/http-commons";
const url = "plan";
const local = localAxios();

async function insertPlan(param, success, fail) {
  console.log("param", param);
  await local.post(url, JSON.stringify(param)).then(success).catch(fail);
  //console.log('userConfirm ok');
}

async function getPlanList (param, success, fail) {
  await local.get(`${url}/list?userId=${param}`).then(success).catch(fail);
}

async function getPlanDetail(param, success, fail){
  await local.get(`${url}/detail/${param}`)
  .then(success)
  .catch(fail);
}

async function getLocationDetail(param, success,fail){
  await local.get(`${url}/location?contentId=${param}`)
  .then(success)
  .catch(fail)
}
export { insertPlan, getPlanList, getPlanDetail, getLocationDetail };
