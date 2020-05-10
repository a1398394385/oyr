let app = new Vue({
    el: '#app',
    data: {
        name: "",
        password: "",
    },
    computed: {
        isCorrectPass: function () {
            return this.password.length >= 8
        },
        passwordStyle: function () {
            if (this.password.length != 0 && !this.isCorrectPass)
                return 'border: 1px solid red';
        }
    },
    methods: {
        login: function () {

        }
    }
});