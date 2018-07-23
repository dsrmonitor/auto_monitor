using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TCPSocketTest
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            IPAddress localAddr = IPAddress.Parse("127.0.0.1");
            TcpListener servidor = new TcpListener(8686);
            servidor.Start();

            while (true)
            {
                Socket conexao = servidor.AcceptSocket();

                //NetworkStream socketStream = new NetworkStream(conexao);
                byte[] bytes = new byte[256];
                conexao.Receive(bytes);




                conexao.Close();

                System.Threading.Thread.Sleep(5000);
            }

        }
    }
}
