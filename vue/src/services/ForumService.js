import axios from 'axios';


export default {
        //list of all forums
        list() {
            return axios.get('forums/');
        },
        //returns user to specified forum_id
        get(id) {
            return axios.get(`forums/${id}`);
        },
        //create new forum
        create(topic) {
            const url = 'forums/';
            return axios.post(url, topic);
        },
        getFavorited(userId) {
            const url = `forums/${userId}/favorited`
            return axios.get(url);
        },
        getTrendingForums() {
            const url = 'forums/trending';
            return axios.get(url);
        }

}