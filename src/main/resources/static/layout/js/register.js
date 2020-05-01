let app = new Vue({
    el: 'form',
    data: {
        username: "",
        telephone: "",
        password: "",
        address: "",
    },
    watch: {
        password(newValue, oldValue) {
            if (newValue != null)
                this.password = newValue.replace(/\s+/g, "");
        },
        telephone(newValue, oldValue) {
            if (newValue != null)
                this.telephone = newValue.replace(/\s+/g, "");
        },
        username(newValue, oldValue) {
            if (newValue != null)
                this.username = newValue.replace(/\s+/g, "");
        },
        address(newValue, oldValue) {
            if (newValue != null)
                this.address = newValue.replace(/\s+/g, "");
        },
    },
    computed: {
        disabled: function () {
            return !this.isCorrectName ||
                !this.isCorrectTel ||
                !this.isCorrectPass ||
                !this.isCorrectAddress
        },
        isCorrectName: function () {
            return this.username != ""
        },
        isCorrectTel: function () {
            return /^1(3|4|5|6|7|8|9)\d{9}$/.test(this.telephone)
        },
        isCorrectPass: function () {
            return this.password.length >= 8
        },
        isCorrectAddress: function () {
            return this.address != ""
        },
        disabledStyle: function () {
            if (this.disabled)
                return 'background-color: #E6E6E6';
        },
        usernameStyle: function () {
            if (false)
                return 'border: 1px solid red';
        },
        telephoneStyle: function () {
            if (this.telephone.length != 0 && !this.isCorrectTel)
                return 'border: 1px solid red';
        },
        passwordStyle: function () {
            if (this.password.length != 0 && !this.isCorrectPass)
                return 'border: 1px solid red';
        },
        addressStyle: function () {
            if (false)
                return 'border: 1px solid red';
        }
    }
});


$(function () {
    $(":submit").click((event) => {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: "/user/",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify($('form').serializeJSON()),
            dataType: "json",
            success: (response) => {
                if (response.status == "success")
                    window.location.href = '/login';
                if (response.status == "filed")
                    alert(response.message);
            }
        });
    })
});