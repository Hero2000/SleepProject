import asyncio
import websockets

# 定义 WebSocket 服务器处理函数
async def echo(websocket):
    # print(f"进入 echo 函数，path 参数值为: {path}")
    try:
        # 接收客户端消息
        async for message in websocket:
            print(f"收到消息: {message}")
            # 向客户端发送响应消息
            await websocket.send(f"服务器收到消息: {message}")
    except websockets.exceptions.ConnectionClosedOK:
        print("连接正常关闭")
    except Exception as e:
        print(f"发生错误: {e}")

# 主函数，用于启动服务器
async def main():
    # 启动 WebSocket 服务器，监听本地地址和端口 5000
    server = await websockets.serve(echo, "172.23.52.13", 5000)
    print("WebSocket 服务器已启动，监听地址: ws://172.23.52.13:5000")
    # 保持服务器运行
    await server.wait_closed()

# 运行主函数
if __name__ == "__main__":
    asyncio.run(main())

