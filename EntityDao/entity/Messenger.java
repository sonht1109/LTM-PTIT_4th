/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author phamthainb
 */
@Entity
@Table(name = "messenger")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messenger.findAll", query = "SELECT m FROM Messenger m"),
    @NamedQuery(name = "Messenger.findById", query = "SELECT m FROM Messenger m WHERE m.id = :id"),
    @NamedQuery(name = "Messenger.findByCreatedAt", query = "SELECT m FROM Messenger m WHERE m.createdAt = :createdAt"),
    @NamedQuery(name = "Messenger.findByUpdatedAt", query = "SELECT m FROM Messenger m WHERE m.updatedAt = :updatedAt"),
    @NamedQuery(name = "Messenger.findByNickName", query = "SELECT m FROM Messenger m WHERE m.nickName = :nickName")})
public class Messenger implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "nick_name")
    private String nickName;
    @JoinColumn(name = "chat_box_id", referencedColumnName = "id")
    @ManyToOne
    private ChatBox chatBoxId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;
    @OneToMany(mappedBy = "messenger")
    private Collection<Message> messageCollection;

    public Messenger() {
    }

    public Messenger(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public ChatBox getChatBoxId() {
        return chatBoxId;
    }

    public void setChatBoxId(ChatBox chatBoxId) {
        this.chatBoxId = chatBoxId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messenger)) {
            return false;
        }
        Messenger other = (Messenger) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Messenger[ id=" + id + " ]";
    }
    
}
