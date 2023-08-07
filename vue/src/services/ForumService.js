import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000/"
});

export default {
        //list of all forums
        list() {
            return http.get('/forums');
        },
        //returns user to specified forum_id
        get(id) {
            return http.get(`/forums/${id}`);
        },
        //create new forum
        create(topic) {
            const url = '/forums';
            return http.post(url, topic);
        }

}