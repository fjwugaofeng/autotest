package com.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
//import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**   java ssh登录linux以后的一些操作方式
* 
* @author 牵手无奈
* @date 2016年12月1日上午10:21:36 
* @version V1.0   
*/
public class SSHHelper{
     private final static Log logger =LogFactory.getLog(SSHHelper.class);

//     private String     charset = Charset.defaultCharset().toString();
     private Session session = null;
//     private Channel channel = null;
     
     /**
      * 构建不同的Channel
      * @param host
      * @param port
      * @param user
      * @param password
      * @param channelType
      * @throws JSchException 1:表示执行命令，2表示传输文件的
      */
     public SSHHelper(String host, Integer port, String user, String password) {
    	 try {
			connect(host, port, user, password);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        /* if (channelType==1) {
        	 channel = session.openChannel("exec");
		}else if (channelType==2) {
			channel = session.openChannel("sftp");
			channel.connect();
		}*/
//     	channel.connect();
     	
     }

     /**
         * 连接sftp服务器
         * @param host 远程主机ip地址
         * @param port sftp连接端口，null 时为默认端口
         * @param user 用户名
         * @param password 密码
         * @return
         * @throws JSchException 
         */
        private Session connect(String host, Integer port, String user, String password) throws JSchException{
            try {
                JSch jsch = new JSch();
                if(port != null){
                    session = jsch.getSession(user, host, port.intValue());
                }else{
                    session = jsch.getSession(user, host);
                }
                session.setPassword(password);
                //设置第一次登陆的时候提示，可选值:(ask | yes | no)
                session.setConfig("StrictHostKeyChecking", "no");
                //30秒连接超时
                session.connect(30000);
            } catch (JSchException e) {
                e.printStackTrace();
                System.out.println("SFTPUitl 获取连接发生错误");
                throw e;
            }
            return session;
        }

        /*public SSHResInfo sendCmd(String command) throws Exception{
            return sendCmd(command, 200);
        }*/
        /*
        * 执行命令，返回执行结果
        * @param command 命令
        * @param delay 估计shell命令执行时间
        * @return String 执行命令后的返回
        * @throws IOException
        * @throws JSchException
        */
       /* public SSHResInfo sendCmd(String command,int delay) throws Exception{
            if(delay <50){
                delay = 50;
            }
            SSHResInfo result = null;
            byte[] tmp = new byte[1024]; //读数据缓存
            StringBuffer strBuffer = new StringBuffer();  //执行SSH返回的结果
            StringBuffer errResult=new StringBuffer();

            Channel channel = session.openChannel("exec");
            ChannelExec ssh = (ChannelExec) channel;
            //返回的结果可能是标准信息,也可能是错误信息,所以两种输出都要获取
            //一般情况下只会有一种输出.
            //但并不是说错误信息就是执行命令出错的信息,如获得远程java JDK版本就以
            //ErrStream来获得.
            InputStream stdStream = ssh.getInputStream();
            InputStream errStream = ssh.getErrStream();  

            ssh.setCommand(command);
            ssh.connect();

            try {


                //开始获得SSH命令的结果
                while(true){
                //获得错误输出
                    while(errStream.available() > 0){
                        int i = errStream.read(tmp, 0, 1024);
                        if(i < 0) break;
                        errResult.append(new String(tmp, 0, i));
                    }

                   //获得标准输出
                    while(stdStream.available() > 0){
                        int i = stdStream.read(tmp, 0, 1024);
                        if(i < 0) break;
                        strBuffer.append(new String(tmp, 0, i));
                    }
                    if(ssh.isClosed()){
                        int code = ssh.getExitStatus();
                        logger.info("exit-status: " + code);
                        result = new SSHResInfo(code, strBuffer.toString(), errResult.toString());
                        break;
                    }
                    try
                    {
                        Thread.sleep(delay);
                    }
                    catch(Exception ee)
                    {
                        ee.printStackTrace();
                    }
                }
            } finally {
                // TODO: handle finally clause
                channel.disconnect();
            }

            return result;
        }*/
        
        /**
         * 返回执行shell命令之后返回结果，返回状态码，或者返回结果，或者返回错误
         * @param command 执行ssh的命令
         * @param returnType 1表示返回状态吗，2表示返回结果，3表示放回错误
         * @return
         * @throws Exception
         */
        public String sendCmd(String command,int returnType){
            int delay =200;
            String result = null;
            byte[] tmp = new byte[1024]; //读数据缓存
            Channel channel = null;
            InputStream stdStream =null;
            InputStream errStream=null;
            try {
            	
            	StringBuffer strBuffer = new StringBuffer();  //执行SSH返回的结果
                StringBuffer errResult=new StringBuffer();
                channel = session.openChannel("exec");
                ChannelExec ssh = (ChannelExec) channel;
                //返回的结果可能是标准信息,也可能是错误信息,所以两种输出都要获取
                //一般情况下只会有一种输出.
                //但并不是说错误信息就是执行命令出错的信息,如获得远程java JDK版本就以
                //ErrStream来获得.
                stdStream = ssh.getInputStream();
                errStream = ssh.getErrStream();  

                ssh.setCommand(command);
                ssh.connect();
            	
                //开始获得SSH命令的结果
                while(true){
                //获得错误输出
                    while(errStream.available() > 0){
                        int i = errStream.read(tmp, 0, 1024);
                        if(i < 0) break;
                        errResult.append(new String(tmp, 0, i));
                    }

                   //获得标准输出
                    while(stdStream.available() > 0){
                        int i = stdStream.read(tmp, 0, 1024);
                        if(i < 0) break;
                        strBuffer.append(new String(tmp, 0, i));
                    }
                    if(ssh.isClosed()){
                        int code = ssh.getExitStatus();
                        String std = strBuffer.toString();
                        String errstd = errResult.toString();
//                        logger.info("exit-status: " + code);
//                        logger.info("stdout: " + std);
//                        logger.info("errout: " + errstd);
                        if (returnType==1) {
                        	//此时返回状态
							result = code+"";
						}else if (returnType==2) {
							result = std;
							// 移除最后的一个换行符
							result = result.substring(0, result.length()-1);
						}else if (returnType==3) {
							result = errstd;
						}
                        break;
                    }
                    try
                    {
                        Thread.sleep(delay);
                    }
                    catch(Exception ee)
                    {
                        ee.printStackTrace();
                    }
                }
            }catch (Exception e) {
				// TODO: handle exception
            	e.printStackTrace();
			}finally {
                // TODO: handle finally clause
				try {
					if (channel!=null) {
						channel.disconnect();
					}
					
					if(stdStream!=null) {
						stdStream.close();
					}
					
					if (errStream!=null) {
						errStream.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            logger.info(result);
            return result;
        }
        
        /**
         * 执行一个shell命令后返回一个list，保护状态码，结果和错误信息
         * @param command
         * @return
         */
        public List<String> sendCmd(String command){

          int delay =200;
          byte[] tmp = new byte[1024]; //读数据缓存
          
          List<String> ls = new ArrayList<String>();
          InputStream stdStream = null;
          InputStream errStream = null;
          Channel channel = null;
          try {
        	  StringBuffer strBuffer = new StringBuffer();  //执行SSH返回的结果
              StringBuffer errResult=new StringBuffer();
              channel = session.openChannel("exec");
              ChannelExec ssh = (ChannelExec) channel;
              //返回的结果可能是标准信息,也可能是错误信息,所以两种输出都要获取
              //一般情况下只会有一种输出.
              //但并不是说错误信息就是执行命令出错的信息,如获得远程java JDK版本就以
              //ErrStream来获得.
              stdStream = ssh.getInputStream();
              errStream = ssh.getErrStream();  

              ssh.setCommand(command);
              ssh.connect();
        	  
              //开始获得SSH命令的结果
              while(true){
              //获得错误输出
                  while(errStream.available() > 0){
                      int i = errStream.read(tmp, 0, 1024);
                      if(i < 0) break;
                      errResult.append(new String(tmp, 0, i));
                  }

                 //获得标准输出
                  while(stdStream.available() > 0){
                      int i = stdStream.read(tmp, 0, 1024);
                      if(i < 0) break;
                      strBuffer.append(new String(tmp, 0, i));
                  }
                  if(ssh.isClosed()){
                      int code = ssh.getExitStatus();
                      String std = strBuffer.toString();
                      String errstd = errResult.toString();
                      logger.info("exit-status: " + code);
                      logger.info("stdout: " + std);
                      logger.info("errout: " + errstd);
                      
                      	ls.add(code+"");
						ls.add(std);
						ls.add(errstd);
                      break;
                  }
                  try
                  {
                      Thread.sleep(delay);
                  }
                  catch(Exception ee)
                  {
                      ee.printStackTrace();
                  }
              }
          }catch (Exception e) {
			// TODO: handle exception
        	  e.printStackTrace();
		} finally {
              // TODO: handle finally clause
              try {
            	  if (channel!=null) {
            		  channel.disconnect();
				}
            	  if (errStream!=null) {
					errStream.close();
				}
            	  if (stdStream!=null) {
					stdStream.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
          }
          return ls;
      }

        /**
         * @param in
         * @param charset
         * @return
         * @throws IOException
         * @throws UnsupportedEncodingException
         */
      /*  private String processStream(InputStream in, String charset) throws Exception {
            byte[] buf = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while (in.read(buf) != -1) {
                sb.append(new String(buf, charset));
            }
            return sb.toString();
        }*/

        public boolean deleteRemoteFIleOrDir(String remoteFile){
            ChannelSftp channel=null;  
             try {  
                 channel=(ChannelSftp) session.openChannel("sftp");  
                 channel.connect();  
                 SftpATTRS sftpATTRS= channel.lstat(remoteFile);  
                 if(sftpATTRS.isDir()){  
                     //目录  
                     logger.debug("remote File:dir");  
                     channel.rmdir(remoteFile);
                     return true;  
                 }else if(sftpATTRS.isReg()){  
                     //文件  
                     logger.debug("remote File:file");  
                     channel.rm(remoteFile);  
                     return true;  
                 }else{  
                     logger.debug("remote File:unkown");  
                     return false;  
                 }  
             }catch (JSchException e) {  
                 if(channel!=null){  
                     channel.disconnect();  
                     session.disconnect();  
                 }
                 logger.error("error",e);  
                return  false;  
             } catch (SftpException e) { 
                 logger.info("meg"+e.getMessage());
                 logger.error("SftpException",e);  
                 return false;
             }  

        }

        /** 
         * 判断linux下 某文件是否存在 
         */  
     public boolean detectedFileExist(String remoteFile) {  

     ChannelSftp channel=null;  
     try {  
         channel=(ChannelSftp) session.openChannel("sftp");  
         channel.connect();  
         SftpATTRS sftpATTRS= channel.lstat(remoteFile);  
         if(sftpATTRS.isDir()||sftpATTRS.isReg()){  
             //目录 和文件  
             logger.info("remote File:dir");  
             return true;  
         }else{  
             logger.info("remote File:unkown");  
             return false;  
         }  
     }catch (JSchException e) {  
         if(channel!=null){  
             channel.disconnect();  
             session.disconnect();  
     }  
        return  false;  
     } catch (SftpException e) {  
         logger.error(e.getMessage());  
     }  
     return false;  
     } 

     /**
 	 * sftp上传文件(夹)
 	 * @param directory
 	 * @param uploadFile
 	 * @throws Exception 
 	 */
 	public void upload(String directory, String uploadFile) throws Exception{
// 		System.out.println("sftp upload file [directory] : "+directory);
// 		System.out.println("sftp upload file [uploadFile] : "+ uploadFile);
 		Channel channel = null;
 		try {
// 			sftp = (ChannelSftp) channel;
 			channel = session.openChannel("sftp");
 			channel.connect();
 	 		ChannelSftp sftp =  (ChannelSftp) channel;
 	 		File file = new File(uploadFile);
 			if(file.exists()){
 	 			//这里有点投机取巧，因为ChannelSftp无法去判读远程linux主机的文件路径,无奈之举
 	 			try {
 	 				Vector content = sftp.ls(directory);
 	 				if(content == null){
 	 					sftp.mkdir(directory);
 	 				}
 	 			} catch (SftpException e) {
 	 				sftp.mkdir(directory);
 	 			}
 	 			//进入目标路径
 	 			sftp.cd(directory);
 	 			if(file.isFile()){
 	 				InputStream ins = null;
 	 				try {
 	 					ins = new FileInputStream(file);
 	 					//中文名称的
 	 	 				sftp.put(ins, new String(file.getName().getBytes(),"UTF-8"));
 	 	 				//sftp.setFilenameEncoding("UTF-8");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					} finally {
						ins.close();
					}
 	 			}else{
 	 				File[] files = file.listFiles();
 	 				for (File file2 : files) {
 	 					String dir = file2.getAbsolutePath();
 	 					logger.info(dir);
 	 					String newpath = "";
 	 					if(file2.isDirectory()){
 	 						String str = dir.substring(dir.lastIndexOf(file2.separator)+1);
 	 						logger.info(str);
// 	 						directory = FileUtil.normalize(directory + str);
 	 						newpath = directory+"/"+str;
 	 						upload(newpath,dir);
 	 					}
 	 					upload(directory,dir);
 	 				}
 	 			}
 	 		}else {
				logger.info("需要上传的文件不存在！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (channel!=null) {
				channel.disconnect();
			}
		}
 	}

 	/**
 	 * 文件下载，只能下载文件
 	 * @param srcFile
 	 * @param saveFile
 	 * @throws UnsupportedEncodingException
 	 */
 	public void download(String srcFile, String saveFile) throws Exception {
 		Channel channel = null;
 		channel = session.openChannel("sftp");
 		channel.connect();
 		ChannelSftp sftp =  (ChannelSftp) channel;
 		Vector conts = null;
		try{
			conts = sftp.ls(srcFile);
		} catch (SftpException e) {
			e.printStackTrace();
			logger.debug("ChannelSftp sftp罗列文件发生错误",e);
		}
		File file = new File(saveFile);
		if(!file.exists()) file.mkdir();
		//文件
		if(srcFile.indexOf(".") > -1){
			try {
				sftp.get(srcFile, saveFile);
			} catch (SftpException e) {
				e.printStackTrace();
				logger.debug("ChannelSftp sftp下载文件发生错误",e);
			} finally {
				if (channel!=null) {
					channel.disconnect();
				}
			}
		}
	}


     /**
     * 用完记得关闭，否则连接一直存在，程序不会退出
     */
    public void close(){
        if(session.isConnected())
        session.disconnect();
     }


}