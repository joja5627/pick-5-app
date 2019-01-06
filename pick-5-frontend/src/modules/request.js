import axios  from 'axios'
export const REGISTER_USER = 'request/REGISTER_USER'

const registrationSubmit = () => {
    return {
      type: `${REGISTER_USER}_PENDING`
    }
};
const registrationSubmitFulfilled = (parsed) => {
    return{
      type: `${REGISTER_USER}_FULFILLED`,
      payload: { parsed }
    }
};
const registrationSubmitFailed = error => {
    return {
      type: `${REGISTER_USER}_FAILED`,
      payload: { error }
    }
};

const initialState = {
    isRegistering: false
}

export default (state = initialState, action) => {
    switch (action.type) {
       
      case `${REGISTER_USER}_PENDING`:
      console.log(`${REGISTER_USER}_PENDING`)
        return {
          ...state,
          isRegistering: true,
          type:`${REGISTER_USER}_PENDING`
        }
      case `${REGISTER_USER}_FULFILLED`:
      console.log(`${REGISTER_USER}_FULFILLED`)
        return {
          ...state,
          isRegistering: false,
          type:`${REGISTER_USER}_FULFILLED`
  
        }
      case `${REGISTER_USER}_FAILED`:
      console.log(`${REGISTER_USER}_FAILED`)
        return {
          ...state,
          isRegistering: false,
          type:`${REGISTER_USER}_FAILED`
        }
      default:
        return state
    }
  }

export const submitUserRegistration = subreddit => dispatch => {

    dispatch(registrationSubmit(subreddit))
    console.log(subreddit)
    
    return axios({
        method: 'post',
        url: `http://registration-app/api/v1/register`,
        data: {
          email: 'Fred',
          password: 'Flintstone'
        }
      }).then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });;

  }
// unction campaignsAPI(data) {
//     checkCookieForValues();
//     const url = `/api/accounts/${data.accountId}/campaigns`;
  

  
//     if (data.placementType) {
//       options.url = `${url}?placementType=${data.placementType}`;
//     }
  
//     return axios(options).then(response => {
//       if (data.campaignId && data.method === 'GET') {
//         return formatCampaignFromResponse(response.data.results);
//       } else if (data.method === 'DELETE') {
//         return data.body;
//       }
  
//       return _.get(response, 'data.results', []);
//     });
//   }