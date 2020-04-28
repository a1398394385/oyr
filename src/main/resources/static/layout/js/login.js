let app = new Vue({
    el: 'form',
    data: {
        telephone: "",
        password: "",
    },
    computed: {
        disabled: function () {
            return !this.isCorrectTel ||
                !this.isCorrectPass
        },
        isCorrectTel: function () {
            return /^1(3|4|5|6|7|8|9)\d{9}$/.test(this.telephone)
        },
        isCorrectPass: function () {
            return this.password.length >= 8
        },
        disabledStyle: function () {
            if (this.disabled)
                return 'background-color: #E6E6E6';
        },
        telephoneStyle: function () {
            if (this.telephone.length != 0 && !this.isCorrectTel)
                return 'border: 1px solid red';
        },
        passwordStyle: function () {
            if (this.password.length != 0 && !this.isCorrectPass)
                return 'border: 1px solid red';
        }
    }
});


$(function () {
    $(":submit").click((event) => {
        event.preventDefault();
        $.ajax({
            type: "post",
            url: "/login",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify($('form').serializeJSON()),
            dataType: "json",
            success: (response) => {
                if (response.status == "success") {
                    localStorage.setItem("session", JSON.stringify(response.data))
                    window.location.href = '/home'
                }

                if (response.status == "filed")
                    alert(response.message);
            }
        });
    })
});