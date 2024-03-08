import Head from "next/head";
import { ReactElement, useState, useEffect } from "react";
import styles from "../../styles/Mensalistas.module.css";
import Layout from "@/components/Layout";
import Table from "@/components/Table";
import Modal from "@/components/Modal";

export default function Home(): ReactElement {
  const [width, setWidth] = useState<number>(0)
  const [active, setActive] = useState<boolean>(false)
  const [modalOpen, setModalOpen] = useState(false);

  useEffect(() => {
      const handleResize = () => {
        setWidth(window.innerWidth);
      };
  
      if (typeof window !== 'undefined') {
        window.addEventListener('resize', handleResize);
  
        setWidth(window.innerWidth);
  
        return () => {
          window.removeEventListener('resize', handleResize);
        };
      }
  }, [])

  return (
    <>
        <Head>
            <title>SysPark - Mensalistas</title>
            <meta name="description" content="Sistema SysPark" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="icon" href="/img/Parking.svg" />
        </Head>

        <Layout>
            
            <div className={styles.container}>
     
              <div className={styles.headerLogo}>
                <div className={styles.inputContainer}>
                  <label htmlFor="">Filtar: </label>
                  <input placeholder="Digite o nome..." type="text" />
                </div>
                <button onClick={() => setModalOpen(true)} className={styles.addButton}>+ Adicionar</button>
              </div>
            </div>
            
            { modalOpen && 
            <Modal title={"Adicionar"} setModalOpen={setModalOpen}> 
              <div className={styles.modalContainer}>
                <div className={styles.modalInputContainer}>
                  <label htmlFor="">Nome: </label>
                  <input type="text" />
                </div>
                <div className={styles.modalInputContainer}>
                  <label htmlFor="">CPF: </label>
                  <input type="text" />
                </div>
              </div>

              <div className={styles.modalContainer}>
                <div className={styles.modalInputContainer}>
                  <label htmlFor="">Email: </label>
                  <input type="text" />
                </div>
                <div className={styles.modalInputContainer}>
                  <label htmlFor="">Telefone: </label>
                  <input type="text" />
                </div>
              </div>
              <div className={styles.modalContainer}>
                <div className={styles.modalInputContainer}>                
                  <label htmlFor="">Dia do Pagamento: </label>
                  <select name="" id="">
                    <option value="">1</option>
                    <option value="">2</option>
                  </select>
                </div>
              </div>
              
              
            </Modal>
            }
            
            <Table/>
        </Layout>
    </>
  );
}
