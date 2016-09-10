(function(){
	 //初始变量
        var canvas = document.getElementById("canvas"),
            c = canvas.getContext('2d'),
            timer,
           mouseX,
            mouseY,
            canvasWidth,
            canvasHeight,
            maxRadius = 35,
            colorArray = ['#9DDCDC', '#FFF4E1', '#FFEBB7', '#E67A7A'],  //气泡颜色数组
            circleArray = [];   //气泡空数组
        var myCircle = new Circle(30,80,10);

        //自适应窗口
        window.addEventListener("resize",resizeCanvas,false);

        function resizeCanvas(){
            if (!!canvas) {
                canvasHeight = canvas.height = window.innerHeight;//window.innerHeight
                canvasWidth = canvas.width = window.innerWidth;
            }
            updateAll();
        }

        /*鼠标移动
        canvas.onmousemove = function(e) {
            mouseX = e.clientX;
            mouseY = e.clientY;
        }*/

        //气泡函数
        function Circle(xCoordinate, yCoordinate, radius) {
            var randomNumber = Math.floor((Math.random() * 4));         //0-4随机数
            var randomTrueOrFalse = Math.floor(Math.random() * 2);      //0-2随机数
            var randomTrueOrFalseTwo = Math.floor(Math.random() * 2);   //0-2随机数

            this.xCoordinate = xCoordinate;                             //x轴坐标
            this.yCoordinate = yCoordinate;                             //y轴坐标
            this.radius = radius;                                       //气泡半径（决定气泡大小）
            this.color = colorArray[randomNumber];                      //随机气泡颜色值

            //气泡移动x坐标判断增减
            if (randomTrueOrFalse == 1) {
                this.xVelocity = -Math.random() * 1;
            } else {
                this.xVelocity = Math.random() * 1;
            }

            //气泡移动y坐标判断增减
            if (randomTrueOrFalse == 1) {
                this.yVelocity = -Math.random() * 1;
            } else {
                this.yVelocity = Math.random() * 1;
            }

            //距离接近0时，增加半径

            this.update = function() {
                this.xCoordinate += this.xVelocity;         //气泡x坐标，随着随机数的 True Or False 的判断，在改变
                this.yCoordinate += this.yVelocity;         //气泡y坐标，随着随机数的 True Or False 的判断，在改变
                var xDistance = mouseX - this.xCoordinate;  //x距离（鼠标x坐标 -  气泡x坐标 = 距离）
                var yDistance = mouseY - this.yCoordinate;  //y距离（鼠标y坐标 -  气泡y坐标 = 距离）
                var originalRadius = radius;
                

                //移动函数
                if (this.xCoordinate + this.radius > canvasWidth || this.xCoordinate - this.radius < 0) {
                    this.xVelocity = -this.xVelocity;
                };  
                if (this.yCoordinate + this.radius > canvasHeight || this.yCoordinate - this.radius < 0) {
                    this.yVelocity = - this.yVelocity;  
                };

                //半径增减函数，气泡最大半径 maxRadius  = 35
                if (xDistance < 50 && xDistance > -50 && this.radius < maxRadius && yDistance < 50 && yDistance > -50) {
                    this.radius += 2;
                } else if ((xDistance >= 50 && originalRadius < this.radius) || (xDistance <= -50 && originalRadius < this.radius) || (yDistance >= 50 && originalRadius < this.radius) || (yDistance <= -50 && originalRadius < this.radius)) {
                    this.radius -= 2; 
                };

                this.draw();
            }

            //绘制气泡
            this.draw = function() {
                c.beginPath();
                c.arc(this.xCoordinate, this.yCoordinate, this.radius, 0, Math.PI * 2)
                c.fillStyle = this.color;
                c.fill();
            }
        }

        //气泡数量函数
        function count(){
            //循环气泡数量，将随机数据 push 到 circleArray 数组中
            for (var i = 0; i < 800; i++) {
                var randomXCoordinate = Math.random() * canvasWidth;
                var randomYCoordinate = Math.random() * canvasHeight;
                console.log(canvasWidth)
                var randomRadius = Math.random() * 5;
                circleArray.push(new Circle(randomXCoordinate,randomYCoordinate ,randomRadius))
            }
        }

        //更新函数
        function updateAll() {
            c.clearRect(0,0, canvasWidth, canvasHeight);    //清空 Canvas
            myCircle.update();
            for (var i = 0; i < circleArray.length; i++) {
                    circleArray[i].update();
                }
            clearTimeout(timer);
            timer = setTimeout(updateAll, 8)
        }
        resizeCanvas();
        count()
        updateAll();

        canvas.style.opacity = .3;
        canvas.style.filter = "alpha(opacity=30)";
})();
