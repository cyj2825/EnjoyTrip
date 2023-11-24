import { localAxios } from "@/util/http-commons";

const local = localAxios(); // axios instance

const url = "/place";
function listSpring(success, fail) {
    local.get(`${url}/spring`).then(success).catch(fail);
}

function listSummer(success, fail) {
    local.get(`${url}/summer`).then(success).catch(fail);
}

function listAutumn(success, fail) {
    local.get(`${url}/autumn`).then(success).catch(fail);
}

function listWinter(success, fail) {
    local.get(`${url}/winter`).then(success).catch(fail);
}
export {
    listSpring,
    listSummer,
    listAutumn,
    listWinter
};